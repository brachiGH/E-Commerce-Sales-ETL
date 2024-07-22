from cassandra.cluster import Cluster
from cassandra.query import SimpleStatement
from uuid import uuid4

# Connect to Cassandra
cluster = Cluster(['127.0.0.1'])  # Replace with your Cassandra node IP(s)
session = cluster.connect()

# Create a keyspace
keyspace_creation_query = """
CREATE KEYSPACE IF NOT EXISTS example_keyspace
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}
"""
session.execute(keyspace_creation_query)
print("Keyspace created")

# Use the keyspace
session.set_keyspace('example_keyspace')

# Create a table
table_creation_query = """
CREATE TABLE IF NOT EXISTS example_table (
    id UUID PRIMARY KEY,
    name TEXT,
    age INT
)
"""
session.execute(table_creation_query)
print("Table created")

# Insert data
insert_query = """
INSERT INTO example_table (id, name, age) VALUES (?, ?, ?)
"""
prepared = session.prepare(insert_query)

# Insert some rows
session.execute(prepared, (uuid4(), 'Alice', 30))
session.execute(prepared, (uuid4(), 'Bob', 25))
print("Data inserted")

# Retrieve data
select_query = "SELECT * FROM example_table"
rows = session.execute(select_query)

# Print results
for row in rows:
    print(f"id: {row.id}, name: {row.name}, age: {row.age}")

# Close the connection
cluster.shutdown()
