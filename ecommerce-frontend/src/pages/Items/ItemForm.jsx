import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getItemById, createItem, updateItem } from "../../api/items.service";

export default function ItemForm() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [item, setItem] = useState({ name: "", price: "", description: "" });
  const [loading, setLoading] = useState(Boolean(id));
  const [error, setError] = useState(null);

  useEffect(() => {
    if (id) {
      getItemById(id)
        .then((res) => setItem(res.data))
        .catch(() => setError("Error loading item"))
        .finally(() => setLoading(false));
    }
  }, [id]);

  const handleChange = (e) => setItem({ ...item, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    const action = id ? updateItem(id, item) : createItem(item);
    action
      .then(() => navigate("/items"))
      .catch(() => setError("Error saving item"));
  };

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        width: "100vw",
        height: "100vh",
        padding: "20px",
        boxSizing: "border-box",
      }}
    >
      <div style={{ maxWidth: "600px", width: "100%", textAlign: "center" }}>
        <h1>{id ? "Edit Item" : "Create New Item"}</h1>
        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}
        {!loading && (
          <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", width: "100%" }}>
            <input
              name="name"
              value={item.name}
              onChange={handleChange}
              placeholder="Name"
              style={{ marginBottom: "10px", padding: "10px", width: "100%" }}
            />
            <input
              name="price"
              value={item.price}
              onChange={handleChange}
              placeholder="Price"
              style={{ marginBottom: "10px", padding: "10px", width: "100%" }}
            />
            <textarea
              name="description"
              value={item.description}
              onChange={handleChange}
              placeholder="Description"
              style={{ marginBottom: "10px", padding: "10px", width: "100%" }}
            />
            <button type="submit">{id ? "Update" : "Create"} Item</button>
          </form>
        )}
        <button onClick={() => navigate("/items")} style={{ marginTop: "20px" }}>
          Back
        </button>
      </div>
    </div>
  );
}