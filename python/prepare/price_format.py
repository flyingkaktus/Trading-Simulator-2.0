import csv

filename = "result_file.csv"
new_filename = "modified_example.csv"

# Öffnen Sie die CSV-Datei in einem Lesemodus
with open(filename, "r") as csvfile:
    # Lesen Sie die CSV-Datei mit einem CSV-Leser
    reader = csv.reader(csvfile)
    
    # Überschriftenzeile extrahieren
    headers = next(reader)
    
    # Index des Preisspalte ermitteln
    price_index = headers.index("price")
    
    # Lesen Sie jede Zeile in der CSV-Datei
    rows = [row for row in reader]
    
    # Formatieren Sie die Preisspalte
    for row in rows:
        row[price_index] = "{:.3f}".format(float(row[price_index]))

# Öffnen Sie die CSV-Datei in einem Schreibmodus
with open(new_filename, "w") as csvfile:
    # Initialisieren Sie einen CSV-Schreiber
    writer = csv.writer(csvfile)
    
    # Schreiben Sie die Überschriftenzeile in die neue CSV-Datei
    writer.writerow(headers)
    
    # Schreiben Sie jede Zeile in die neue CSV-Datei
    for row in rows:
        writer.writerow(row)

print("Die Änderungen wurden erfolgreich gespeichert.")
