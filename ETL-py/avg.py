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
    query = """
        SELECT r.id AS review_id, r.rate, r._date, r.product_id, p.name AS product_name, p.price AS product_price
        FROM reviews r
        JOIN products p ON r.product_id = p.id
    """
    df = pd.read_sql(query, source_conn)
    source_conn.close()
    return df

def transform_data(df):
    # Count number of reviews and calculate average rating for each product
    review_summary = df.groupby('product_id').agg(
        review_count=('review_id', 'count'),
        average_rating=('rate', 'mean'),
        product_name=('product_name', 'first'),
        product_price=('product_price', 'first')
    ).reset_index()
    return review_summary

def load_data(df):
    # Connect to the destination database
    dest_engine = create_engine(
        f"mysql+mysqlconnector://{destination_db_config['user']}:{destination_db_config['password']}@{destination_db_config['host']}:{destination_db_config['port']}/{destination_db_config['database']}"
    )
    df.to_sql('product_review_summary', dest_engine, if_exists='replace', index=False)

def create_product_review_summary_table():
    # Connect to the destination database
    dest_conn = mysql.connector.connect(**destination_db_config)
    cursor = dest_conn.cursor()
    cursor.execute(
        """
        CREATE TABLE IF NOT EXISTS product_review_summary (
            id INT AUTO_INCREMENT PRIMARY KEY,
            product_id BIGINT,
            product_name VARCHAR(255),
            product_price DOUBLE,
            review_count INT,
            average_rating FLOAT
        )
        """
    )
    dest_conn.commit()
    dest_conn.close()

def etl_pipeline():
    df = extract_data()
    transformed_df = transform_data(df)
    create_product_review_summary_table()
    load_data(transformed_df)

if __name__ == "__main__":
    etl_pipeline()
