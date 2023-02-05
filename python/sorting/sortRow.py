import csv

with open("data/csv/unsorted_rows.csv", "r") as input_file:
    reader = csv.reader(input_file)
    data = [row for row in reader]

num_cols = len(data[0])
sorted_data = [sorted(col) for col in zip(*data)]

with open("data/csv/sorted_csv/sorted_rows.csv", "w", newline="") as output_file:
    writer = csv.writer(output_file)
    for i in range(num_cols):
        writer.writerow([val[i] for val in sorted_data])
