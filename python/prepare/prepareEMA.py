import pandas as pd

# Lese die CSV-Datei ein
df = pd.read_csv("data/csv/output_EMA.csv")

# Filter die Zeilen, bei denen EMA nicht 0 ist
df = df[df['EMA_10000'] != 0]

# Überspringe die nächsten 500 Zeilen
df = df.iloc[10000:]

# Schreibe die gefilterten Zeilen in eine neue CSV-Datei
df.to_csv("data/csv/rdy_for_app.csv", index=False)
