import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import { Redirect, Route, Switch } from "react-router-dom";
import NavbarComponent from "./components/navbar";
import NotFoundComponent from "./components/404";
import OrderResultComponent from "./components/order/orderResult";
import CartItemsComponent from "./components/cart/cartItems";
import ProductCartItemsComponent from "./components/home/productItems";
import ProductDetailsComponent from "./components/details/productDetails";
import loadProductList from "./data/productData";
import "./App.css";
import axios from "axios";

class App extends Component {
  constructor(props) {
    super(props);

    this.state = {
      orderedItems: [],
      productItems: [],
      productOrders: {},
      searchField: "",
      pageUrl: "/",
    };
    loadProductList(this.setProductList);
  }

  setProductList = (productList) => {
    this.setState({ ...this.state, productItems: productList });
  };

  render() {
    if (!this.state.productItems) {
      return;
    }
    return (
      <React.Fragment>
        <NavbarComponent
          productItems={this.state.productItems}
          searchField={this.state.searchField}
          onSearchValueChange={this.onSearchValueChange}
          pageUrl={this.state.pageUrl}
        />
        <div className="container" style={{ overflowX: "hidden" }}>
          <Switch>
            <Route
              path="/product/:id"
              render={(props) => (
                <ProductDetailsComponent
                  productItems={this.state.productItems}
                  onAddItem={this.handleAddCartItem}
                  {...props}
                />
              )}
            />
            <Route
              path="/"
              exact
              render={(props) => (
                <ProductCartItemsComponent
                  searchField={this.state.searchField}
                  productItems={this.state.productItems}
                  onAddItem={this.handleAddCartItem}
                  {...props}
                />
              )}
            />
            <Route
              path="/cart"
              render={(props) => (
                <CartItemsComponent
                  productItems={this.state.productItems}
                  onBuyItem={this.handleBuyItem}
                  onIncrement={this.onQuantityIncrement}
                  onDecrement={this.onQuantityDecrement}
                  cleanCartItem={this.cleanCartItem}
                  {...props}
                />
              )}
            />
            <Route path="/result" component={OrderResultComponent} />
            <Route path="/not-found" component={NotFoundComponent}></Route>
            <Redirect to="/not-found" />
          </Switch>
        </div>
      </React.Fragment>
    );
  }

  componentDidMount() {
    const pageUrl = this.props.location.pathname;
    this.setState({ pageUrl });
  }
  componentDidUpdate(prevProps) {
    if (this.props.location.pathname !== prevProps.location.pathname) {
      const pageUrl = this.props.location.pathname;
      this.setState({ pageUrl });
    }
  }

  // search field value change
  onSearchValueChange = (e) => {
    this.setState({ searchField: e.currentTarget.value });
  };

  // Add new items to order
  handleBuyItem = async (item) => {
    // tbl_order
    const formJsonData = {
      tableId: 1, // Each Tablet Number
      orderDetails: [],
      orderDate: new Date(),
    };

    // tbl_order_detail
    item.forEach((data) => {
      formJsonData.orderDetails.push({
        prodId: data.id,
        orderDetailPrice: data.price,
        orderCount: data.quantity,
      });
    });

    // send data to server ( json 데이터 보내기 - 방법 확인 필요 )
    const jsonData = JSON.stringify(formJsonData);

    // axios content type
    const config = {
      headers: {
        // Overwrite Axios's automatically set Content-Type
        "Content-Type": "application/json",
      },
    };

    // data send to spring
    await axios.post(
      `http://localhost:8081/connectDatabase/saveOrder`,
      jsonData,
      config
    );

    this.setState({ ...this.state, orderedItems: formJsonData });

    this.props.history.push({
      pathname: "/result",
      state: {
        orderedItems: formJsonData,
      },
    });
  };

  handleReset(item) {
    let currentItems = [...this.state.productItems];
    item.forEach((value) => {
      let index = currentItems.indexOf(value);
      currentItems[index] = { ...value };
      currentItems[index].quantity = 0;
    });
    this.setState({ productItems: currentItems });
  }

  // cart item functionalities
  onQuantityIncrement = (Item) => {
    const productItems = [...this.state.productItems];
    const index = this.state.productItems.indexOf(Item);
    productItems[index] = { ...Item };
    productItems[index].quantity++;
    this.setState({ productItems });
  };

  onQuantityDecrement = (Item) => {
    const productItems = [...this.state.productItems];
    const index = this.state.productItems.indexOf(Item);
    productItems[index] = { ...Item };
    productItems[index].quantity--;
    this.setState({ productItems });
  };

  handleAddCartItem = (product) => {
    const productItems = [...this.state.productItems];
    let itemIndex = -1;
    this.state.productItems.forEach((value, index) => {
      if (value.id === product.id) itemIndex = index;
    });
    productItems[itemIndex].quantity++;
    this.setState({ productItems });
  };

  cleanCartItem = () => {
    const productItems = [...this.state.productItems];
    const ordered = productItems.filter((item) => item.quantity > 0);

    for (const item of ordered) {
      let itemIndex = item.id;
      this.state.productItems.forEach((value, index) => {
        if (value.id === item.id) itemIndex = index;
      });
      productItems[itemIndex].quantity = 0;
    }
    this.setState({ productItems });
  };
}

export default withRouter((props) => <App {...props} />);
