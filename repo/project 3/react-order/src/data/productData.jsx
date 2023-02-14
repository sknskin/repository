/* eslint-disable react-hooks/rules-of-hooks */

// <Tablet Format>
import axios from "axios";

function loadProductList(setState) {
  const loadProductListVar = async () => {
    const response = await axios.get(
      `http://localhost:8081/connectDatabase/loadProductList`
    );

    const list = Array.from(response.data);

    const productList = list.map((product) => {
      return {
        id: product.prodId,
        imgUrl: "/tablet/assets/" + product.savedFileName,
        name: product.prodName,
        details: product.prodDesc,
        quantity: 0,
        ingredients: product.prodIngredients,
        price: product.prodPrice,
        categories: product.prodCategory,
      };
    });
    setState(productList);
  };
  loadProductListVar();
}

export default loadProductList;
