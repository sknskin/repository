import React, { Component } from "react";
import "./../../style/cartItem.css";

class CartItemComponent extends Component {
  render() {
    return (
      <React.Fragment>
        <tr>
          <td>
            <img src={this.props.cartItems.imgUrl} height="72"></img>
          </td>
          <td style={{ display: "table-cell" }}>
            <span>{this.props.cartItems.name}</span>
            <span>
              <p>&#8361;{this.props.cartItems.price}</p>
            </span>
          </td>
          <td>
            <button
              onClick={() => this.props.onIncrement(this.props.cartItems)}
              className="btn btn-light btn-md"
            >
              <b>+</b>
            </button>
            <span className="badge m-4 badge-info">
              {this.props.cartItems.quantity}
            </span>
            <button
              onClick={() => this.props.onDecrement(this.props.cartItems)}
              className="btn btn-light btn-md"
            >
              <b>-</b>
            </button>
          </td>
          <td>
            <p>&#8361;{this.getTotalPrice()}</p>
          </td>
        </tr>
      </React.Fragment>
    );
  }
  getTotalPrice() {
    const { quantity, price } = this.props.cartItems;
    return quantity * price;
  }
}

export default CartItemComponent;
