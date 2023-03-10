import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("data/csv/rdy_for_app.csv")
df['timestamp'] = pd.to_datetime(df['timestamp'], unit='ms')
df = df[df['timestamp'] > pd.to_datetime(1645422280000, unit='ms')]
#df = df[df.EMA != 0].iloc[1000:] # Drop first 1000 rows where EMA is 0
df = df.head(14*24*60)  # Begrenzung auf 1000 Einträge

# Plot data
plt.plot(df["timestamp"], df["price"], label="price")
plt.plot(df["timestamp"], df["EMA_10000"], label="EMA_10000")
plt.plot(df["timestamp"], df["EMA_1440"], label="EMA_1440")
plt.plot(df["timestamp"], df["EMA_720"], label="EMA_720")
plt.plot(df["timestamp"], df["EMA_180"], label="EMA_180")

# Add labels and title
plt.xlabel("Timestamp")
plt.ylabel("Price")
plt.title("Price vs EMA")

plt.legend()
plt.savefig("data/plot_visual/EMA_plt04.png", dpi=600)
