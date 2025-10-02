import { useState, useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { createApparel, createFigure, createManga } from "../../api/items.service";
import { AuthContext } from "../../context/AuthContext";

export default function ItemForm() {
  const { role } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    if (role !== "ROLE_ADMIN") navigate("/items");
  }, [role, navigate]);

  const [type, setType] = useState("apparel");
  const [formData, setFormData] = useState({
    name: "",
    price: "",
    description: "",
    size: "",
    color: "",
    apparelType: "",
    brand: "",
    character: "",
    scale: "",
    author: "",
    volumeNumber: "",
    publisher: "",
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleChange = (e) =>
    setFormData((prev) => ({ ...prev, [e.target.name]: e.target.value }));

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    if (!formData.name || !formData.price || !formData.description) {
      setError("Name, price and description are required");
      return;
    }
    if (Number(formData.price) <= 0) {
      setError("Price must be greater than 0");
      return;
    }
    if (formData.description.length < 10) {
      setError("Description must be at least 10 characters");
      return;
    }

    if (type === "apparel" && (!formData.size || !formData.color || !formData.apparelType)) {
      setError("Size, color and type are required for Apparel");
      return;
    }
    if (type === "figure" && (!formData.brand || !formData.character || !formData.scale)) {
      setError("Brand, character and scale are required for Figure");
      return;
    }
    if (type === "manga" && (!formData.author || !formData.volumeNumber || !formData.publisher)) {
      setError("Author, volume number and publisher are required for Manga");
      return;
    }

    setLoading(true);

    try {
      if (type === "apparel") {
        await createApparel({
          name: formData.name,
          price: Number(formData.price),
          description: formData.description,
          size: formData.size,
          color: formData.color,
          type: formData.apparelType,
        });
      } else if (type === "figure") {
        await createFigure({
          name: formData.name,
          price: Number(formData.price),
          description: formData.description,
          brand: formData.brand,
          character: formData.character,
          scale: formData.scale,
        });
      } else if (type === "manga") {
        await createManga({
          name: formData.name,
          price: Number(formData.price),
          description: formData.description,
          author: formData.author,
          volumeNumber: formData.volumeNumber,
          publisher: formData.publisher,
        });
      }

      navigate("/items");
    } catch (err) {
      setError(err.response?.data?.message || err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ display: "flex", justifyContent: "center", alignItems: "center", width: "100vw", height: "100vh", padding: "20px", boxSizing: "border-box" }}>
      <div style={{ maxWidth: "600px", width: "100%" }}>
        <h2 style={{ textAlign: "center" }}>
          Create New {type.charAt(0).toUpperCase() + type.slice(1)}
        </h2>

        <label style={{ display: "block", marginBottom: "10px" }}>
          Product Type:
          <select
            value={type}
            onChange={(e) => setType(e.target.value)}
            style={{ marginLeft: "10px" }}
          >
            <option value="apparel">Apparel</option>
            <option value="figure">Figure</option>
            <option value="manga">Manga</option>
          </select>
        </label>

        <form
          onSubmit={handleSubmit}
          style={{ display: "flex", flexDirection: "column", gap: "10px", marginTop: "20px" }}
        >
          <input placeholder="Name" name="name" value={formData.name} onChange={handleChange} />
          <input placeholder="Price" name="price" type="number" value={formData.price} onChange={handleChange} />
          <textarea placeholder="Description" name="description" value={formData.description} onChange={handleChange} />

          {type === "apparel" && (
            <>
              <input placeholder="Size" name="size" value={formData.size} onChange={handleChange} />
              <input placeholder="Color" name="color" value={formData.color} onChange={handleChange} />
              <input placeholder="Type" name="apparelType" value={formData.apparelType} onChange={handleChange} />
            </>
          )}

          {type === "figure" && (
            <>
              <input placeholder="Brand" name="brand" value={formData.brand} onChange={handleChange} />
              <input placeholder="Character" name="character" value={formData.character} onChange={handleChange} />
              <input placeholder="Scale" name="scale" value={formData.scale} onChange={handleChange} />
            </>
          )}

          {type === "manga" && (
            <>
              <input placeholder="Author" name="author" value={formData.author} onChange={handleChange} />
              <input placeholder="Volume Number" name="volumeNumber" value={formData.volumeNumber} onChange={handleChange} />
              <input placeholder="Publisher" name="publisher" value={formData.publisher} onChange={handleChange} />
            </>
          )}

          <button type="submit" disabled={loading}>
            {loading ? "Creating..." : `Create ${type}`}
          </button>
          {error && <p style={{ color: "red" }}>{error}</p>}
        </form>
      </div>
    </div>
  );
}