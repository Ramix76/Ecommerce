export default function ProductCard({ product }) {
  return (
    <div style={{ border: "1px solid #ccc", padding: "10px", margin: "10px" }}>
      <h3>{product.name}</h3>
      <p>Price: ${product.price}</p>
    </div>
  );
}