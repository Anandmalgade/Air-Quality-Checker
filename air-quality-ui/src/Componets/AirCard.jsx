function AirCard({ data }) {
  const getAQIColor = (aqi) => {
    if (aqi <= 50) return "#4CAF50";     // Good
    if (aqi <= 100) return "#FFEB3B";    // Moderate
    if (aqi <= 150) return "#FF9800";    // Unhealthy
    if (aqi <= 200) return "#F44336";    // Harmful
    return "#6A1B9A";                    // Hazardous
  };

  return (
    <div
      className="result-card"
      style={{ borderTop: `10px solid ${getAQIColor(data.aqi)}` }}
    >
      <h2>{data.city}</h2>
      <p><strong>AQI:</strong> {data.aqi}</p>
      <p><strong>Category:</strong> {data.category}</p>

      <h3>🧪 Pollutants</h3>
      <ul className="pollutants-list">
        {Object.entries(data.pollutants).map(([key, value]) => (
          <li key={key}>
            <strong>{key.toUpperCase()}:</strong> {value?.v ?? "N/A"}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default AirCard;
