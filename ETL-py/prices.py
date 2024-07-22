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
    'database': 'my_database2',
    'port': 3307
}

def extract_data():
    # Connect to the source database
    source_conn = mysql.connector.connect(**source_db_config)
    query = "SELECT id, name, price FROM products"
    df = pd.read_sql(query, source_conn)
    source_conn.close()
    return df

def transform_data(df):
    # Categorize products into price ranges
    bins = [0, 25, 50, 75, 100, 150, 200, 250, 300, 400, 500, float('inf')]
    labels = ['0-25', '25-50', '50-75', '75-100', '100-150', '150-200', '200-250', '250-300', '300-400', '400-500', '500+']
    df['price_range'] = pd.cut(df['price'], bins=bins, labels=labels, right=False)
    price_counts = df['price_range'].value_counts().reset_index()
    price_counts.columns = ['price_range', 'count']
    return price_counts

def load_data(df):
    # Connect to the destination database
    dest_engine = create_engine(
        f"mysql+mysqlconnector://{destination_db_config['user']}:{destination_db_config['password']}@{destination_db_config['host']}:{destination_db_config['port']}/{destination_db_config['database']}"
    )
    df.to_sql('prices', dest_engine, if_exists='replace', index=False)

def create_prices_table():
    # Connect to the destination database
    dest_conn = mysql.connector.connect(**destination_db_config)
    cursor = dest_conn.cursor()
    cursor.execute(
        """
        CREATE TABLE IF NOT EXISTS prices (
            id INT AUTO_INCREMENT PRIMARY KEY,
            price_range VARCHAR(20),
            count INT
        )
        """
    )
    dest_conn.commit()
    dest_conn.close()

def etl_pipeline():
    df = extract_data()
    transformed_df = transform_data(df)
    create_prices_table()
    load_data(transformed_df)

if __name__ == "__main__":
    etl_pipeline()