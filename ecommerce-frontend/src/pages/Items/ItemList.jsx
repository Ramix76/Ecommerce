import { useEffect, useState } from "react";
import { getItems } from "../../api/items.service";
import { useNavigate } from "react-router-dom";

export default function ItemList({ setMode }) {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    setLoading(true);
    getItems()
      .then(res => setItems(res.data))
      .catch(() => setError("Error loading items"))
      .finally(() => setLoading(false));
  }, []);

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        width: "100vw",
        paddingTop: "80px",
        paddingLeft: "20px",
        paddingRight: "20px",
        boxSizing: "border-box",
      }}
    >
      <div style={{ maxWidth: "600px", width: "100%", textAlign: "center" }}>
        <h1>All Items</h1>
        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}
        {!loading && !error && (
          <ul style={{ listStyle: "none", padding: 0, marginTop: "20px" }}>
            {items.map(item => {
              const name = item.name || item.nombre || item.title;
              return (
                <li key={item.id} style={{ marginBottom: "8px" }}>
                  {name}{" "}
                  <button onClick={() => navigate(`/items/edit/${item.id}`)} style={{ marginLeft: "10px" }}>
                    Edit
                  </button>
                </li>
              );
            })}
          </ul>
        )}
        <button onClick={() => setMode(null)} style={{ marginTop: "20px" }}>
          Back
        </button>
      </div>
    </div>
  );
}