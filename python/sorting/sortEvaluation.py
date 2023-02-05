import csv

filenameIn = "data/csv/output_Evaluation.csv"
filenameOut = "data/csv/sorted_csv/output_Evaluation_Sorted.csv"

with open(filenameIn, "r") as file:
    reader = csv.reader(file)
    data = [row for row in reader]

data.sort(key=lambda x: float(x[-1]), reverse=True)

with open(filenameOut, "w") as file:
    writer = csv.writer(file)
    writer.writerows(data)
