import { useState } from "react";
import ItemMenu from "./ItemMenu";
import ItemList from "./ItemList";
import ItemSearch from "./ItemSearch";

export default function Items() {
  const [mode, setMode] = useState(null);

  if (!mode) {
    return <ItemMenu setMode={setMode} />;
  } else if (mode === "list") {
    return <ItemList setMode={setMode} />;
  } else if (mode === "search") {
    return <ItemSearch setMode={setMode} />;
  }
}