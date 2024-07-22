import mysql.connector
from mysql.connector import Error
import pandas as pd
from sqlalchemy import create_engine

# Database connection configurations
source_db_config = {
    'user': 'user',
    'password': 'password',
    'host': 'localhost',
    'database': 'my_database',
    'port': 3306
}

destination_db_config = {
    'user': 'user',
    'password': 'password',
    'host': 'localhost',
    'database': 'my_database',
    'port': 3307
}

def extract_data():
    # Connect to the source database
    source_conn = mysql.connector.connect(**source_db_config)
    query = "SELECT id, rate, _date, product_id, client_id FROM reviews"
    df = pd.read_sql(query, source_conn)
    source_conn.close()
    return df

def transform_data(df):
    # Count reviews for each rating from 1 to 5
    rating_counts = df['rate'].value_counts().reset_index()
    rating_counts.columns = ['rate', 'count']
    return rating_counts

def load_data(df):
    # Connect to the destination database
    dest_engine = create_engine(
        f"mysql+mysqlconnector://{destination_db_config['user']}:{destination_db_config['password']}@{destination_db_config['host']}:{destination_db_config['port']}/{destination_db_config['database']}"
    )
    df.to_sql('review_counts', dest_engine, if_exists='replace', index=False)

def create_review_counts_table():
    # Connect to the destination database
    dest_conn = mysql.connector.connect(**destination_db_config)
    cursor = dest_conn.cursor()
    cursor.execute(
        """
        CREATE TABLE IF NOT EXISTS review_counts (
            id INT AUTO_INCREMENT PRIMARY KEY,
            rate TINYINT,
            count INT
        )
        """
    )
    dest_conn.commit()
    dest_conn.close()

def etl_pipeline():
    df = extract_data()
    transformed_df = transform_data(df)
    create_review_counts_table()
    load_data(transformed_df)

if __name__ == "__main__":
    etl_pipeline()
