import { useState } from "react";
import api from "../api/api.js";

function CheckConnection() {
  const [status, setStatus] = useState("");

  const checkHealth = async () => {
    try {
      await api.get("/health");
      setStatus("Connection successful ✅");
    } catch (err) {
      setStatus("No connection to the DB ❌");
    }
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        width: "100vw",
        padding: "20px",
        boxSizing: "border-box",
      }}
    >
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          gap: "20px",
          maxWidth: "600px",
          width: "100%",
          textAlign: "center",
        }}
      >
        <h1>Test Connection</h1>
        <button
          onClick={checkHealth}
          style={{
            padding: "10px 20px",
            fontSize: "1rem",
            cursor: "pointer",
          }}
        >
          Test connection
        </button>
        {status && <p>{status}</p>}
      </div>
    </div>
  );
}

export default CheckConnection;