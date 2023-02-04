import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("rdy_for_app.csv")
df['timestamp'] = pd.to_datetime(df['timestamp'], unit='ms')

# df = df[df.EMA != 0].iloc[1000:] # Drop first 1000 rows where EMA is 0
df = df.head(10000)  # Begrenzung auf 1000 Einträge

plt.plot(df['timestamp'], df['price'], label='price')
plt.plot(df['timestamp'], df['EMA'], label='EMA')
plt.legend()
plt.savefig("plots/EMA_plt000.png")
