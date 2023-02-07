import matplotlib.pyplot as plt
import pandas as pd

# Load the CSV file into a Pandas DataFrame
df = pd.read_csv("data/csv/candlestick_data/candlestick_data_ADAUSDT_1m_1613347501000.csv", header=None, names=["time", "value"])
sf = pd.read_csv("sold_data.csv", header=None, names=["time", "value"])

# Convert Unix time to datetime
df['time'] = pd.to_datetime(df['time'], unit='ms')
sf['time'] = pd.to_datetime(sf['time'], unit='ms')

#df = df.iloc[1:]
#df = df.head(60*24*3)

# Plot the data
plt.plot(df["time"], df["value"])

# Mark the specified points with red dots
for _, row in sf.iterrows():
    if row["time"] in df["time"].values:
        time = row["time"]
        value = row["value"]
        plt.plot(time, value, "ro")


# Show the plot
plt.savefig("data/plot_visual/sells_plotted.png", dpi=600)
