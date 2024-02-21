import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { toast } from "react-toastify";
import Sidebar from "../components/Sidebar";
import { addToCart } from "../services/cart";
import { getProducts } from "../services/product";

const ProductList = () => {
  const [products, setProducts] = useState([]);

  // read the redux state
  const status = useSelector((state) => state.auth);
  console.log(`status = `, status);

  const loadProducts = async () => {
    const result = await getProducts();
    if (result["status"] == "success") {
      setProducts(result["data"]);
    } else {
      toast.error(result["error"]);
    }
  };

  const addItemToCart = async (product) => {
    const price = product["mrp"] - (product["mrp"] * product["discount"]) / 100;
    const result = await addToCart(
      product["id"],
      product["mrp"],
      price,
      1,
      price
    );
    if (result["status"] == "success") {
      toast.success("successfully added to your cart");
    } else {
      toast.error(result["error"]);
    }
  };

  useEffect(() => {
    loadProducts();
  }, []);

  return (
    <div>
      <Sidebar />
      <div className="container">
        <h1>Products</h1>
          
            {products.map((product, index) => {
              return (

                <>
                <div className="row">
                  <div className="col-3">
                  <div class="card mb-5" style={{width: 300}}>
                    <img
                      class="card-img-top"
                      style={{ width: 70, height: 70 }}
                      src={"http://localhost:9000/" + product["image"]}
                      alt="..."
                    />
                    <div class="card-body">
                      <h5 class="card-title">{product["title"]}</h5>
                      <p class="card-text">
                      {product["company"]} <br/>
                      ₹ {product["mrp"]} <br/>
                      {product["description"]} 
                      </p>
                      <button
                      onClick={() => {
                        addItemToCart(product);
                      }}
                      className="btn btn-sm btn-success"
                    >
                      add to cart
                    </button>
                    </div>
                  </div>
                  </div>
                </div>
                </>
               
                  



                  
                  
               
              );
            })}
          
      </div>
    </div>
  );
};

export default ProductList;
