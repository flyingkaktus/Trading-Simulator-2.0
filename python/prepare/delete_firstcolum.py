import pandas as pd

# Lade die CSV-Datei
data = pd.read_csv('output_processed_modified_example.csv')

toBeDeleted = "price"

# Entferne die erste Spalte (Timestamp)
data = data.drop(columns=[toBeDeleted])

# Speichere die bearbeitete CSV-Datei
data.to_csv('processed_modified_example.csv', index=False)
