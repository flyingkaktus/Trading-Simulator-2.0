import numpy as np
import pandas as pd

# Lade die CSV-Datei
data = pd.read_csv("processed_modified_example.csv")

# Berechne den Bruchteil (EMA_x / price) * 100 und runde das Ergebnis
for col in data.columns[1:5]:
    data[col] = (data[col] / data["price"] * 100).round()

# Speichere die berechneten Daten in eine neue CSV-Datei
data.to_csv("output_processed_modified_example.csv", index=False)
