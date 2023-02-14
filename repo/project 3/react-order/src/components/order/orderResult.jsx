import { Component } from "react";
import Moment from "moment";
import "moment/locale/ko";

class OrderResultComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      orderedItems: props.location.state.orderedItems,
    };
  }

  render() {
    const formatDate = Moment().format("yyyy-MM-DD / HH:mm:ss");

    return (
      <div>
        <br />
        <br />
        <br />
        <div
          style={{
            textAlign: "center",
            fontSize: "larger",
            fontStyle: "bold",
          }}
        >
          RECEIPT
        </div>
        <div
          style={{ float: "right", fontSize: "larger", marginBottom: "20px" }}
        >
          Table Number : #&nbsp;{this.state.orderedItems.tableId}
          <br />
          Order Date : {formatDate}
          <br />
        </div>
        <br />
        <br />
        <table className="table">
          <thead>
            <tr>
              <th scope="col">ProductId</th>
              <th scope="col">Quantity</th>
              <th scope="col">Price</th>
              <th scope="col">Each Price</th>
            </tr>
          </thead>
          <tbody>
            {this.state.orderedItems.orderDetails.map((orderDetail, idx) => {
              return (
                <tr key={idx}>
                  <td>{orderDetail.prodId}</td>
                  <td>{orderDetail.orderCount}</td>
                  <td>₩&nbsp;{orderDetail.orderDetailPrice}</td>
                  <td>
                    ₩&nbsp;
                    {orderDetail.orderDetailPrice * orderDetail.orderCount}
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
        <hr />
        <br />
        <div style={{ float: "right", fontSize: "x-large" }}>
          Total Price : ₩&nbsp;
          {this.getTotalPrice(this.state.orderedItems.orderDetails)}
        </div>
      </div>
    );
  }

  getTotalPrice(parameter) {
    let totalSum = 0;
    parameter.forEach((Item) => {
      totalSum += Item.orderCount * Item.orderDetailPrice;
    });
    return totalSum;
  }
}

export default OrderResultComponent;
