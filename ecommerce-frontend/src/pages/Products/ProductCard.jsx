export default function ProductCard({ product }) {
  return (
    <div
      style={{
        border: "1px solid #ccc",
        padding: "10px",
        margin: "10px",
        width: "200px"
      }}
    >
      <h3>{product.name || product.title}</h3>
      {product.price && <p>Price: ${product.price.toFixed(2)}</p>}
    </div>
  );
}