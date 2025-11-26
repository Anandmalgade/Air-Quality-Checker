import { useState } from "react";
import AirCard from "./AirCard";
import "../App.css";

function AirQualitySearch() {
  const [city, setCity] = useState("");
  const [data, setData] = useState(null);
  const [error, setError] = useState("");

  const fetchAQI = async () => {
    if (!city.trim()) {
      setError("⚠️ Please enter a city name");
      setData(null);
      return;
    }
    setError("");

    try {
      const response = await fetch(`http://localhost:8181/api/air/${city}`);

      if (!response.ok) {
        setError("❌ City not found!");
        setData(null);
        return;
      }

      const json = await response.json();
      setData(json);
    } catch (err) {
      setError("🚨 Server error! Try again.");
      setData(null);
    }
  };

  return (
    <div className="app-container">
      <h1 className="title">🌍 Air Quality (AQI) Checker</h1>

      <div className="search-box">
        <input
          type="text"
          placeholder="Search city here…"
          value={city}
          onChange={(e) => setCity(e.target.value)}
        />
        <button onClick={fetchAQI}>Search</button>
      </div>

      {error && <p className="error">{error}</p>}

      {data && <AirCard data={data} />}
    </div>
  );
}

export default AirQualitySearch;
