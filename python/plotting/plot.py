import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("data/csv/rdy_for_app.csv")
df['timestamp'] = pd.to_datetime(df['timestamp'], unit='ms')

#df = df[df.EMA != 0].iloc[1000:] # Drop first 1000 rows where EMA is 0
#df = df.head(100000)  # Begrenzung auf 1000 Einträge

# Plot data
plt.plot(df["timestamp"], df["price"], label="price")
plt.plot(df["timestamp"], df["EMA_10000"], label="EMA_10000")
plt.plot(df["timestamp"], df["EMA_250"], label="EMA_250")
plt.plot(df["timestamp"], df["EMA_100"], label="EMA_100")
plt.plot(df["timestamp"], df["EMA_50"], label="EMA_50")

# Add labels and title
plt.xlabel("Timestamp")
plt.ylabel("Price")
plt.title("Price vs EMA")

plt.legend()
plt.savefig("plots/EMA_plt00002.png", dpi=600)
