import React, { Component } from "react";
import ReactImageZoom from "react-image-zoom";
import { Link } from "react-router-dom";
import "./../../style/productDetails.css";

class ProductDetailsComponent extends Component {
  render() {
    let selectedItem = this.getSelectedItem();
    console.log(selectedItem.imgUrl);
    let imgUrl = selectedItem.imgUrl;
    const imageZoomProps = {
      width: 520,
      height: 400,
      img: imgUrl,
    };
    return (
      <div>
        <div className="row mt-5 mb-5">
          <div className="col-12 col-lg-7 col-xl-6">
            <img
              src={selectedItem.imgUrl}
              className="w-100 d-flex d-lg-none d-xl-none justify-content-center"
              alt="img"
            />
            <span className="d-none d-lg-flex d-xl-flex">
              <ReactImageZoom {...imageZoomProps} />
            </span>
            <div className="justify-content-center d-flex mt-5 mb-5">
              {/* <BootstrapModal
                onBuyItem={() => this.props.onBuyItem(selectedItem)}
              /> */}
              <Link to={"/"}>
                <button className="btn btn-secondary btn-sm mr-2">
                  Back to List
                </button>
              </Link>
              &nbsp;&nbsp;
              <button
                onClick={() => this.props.onAddItem(selectedItem)}
                className="btn btn-info btn-sm mr-2 d-flex"
              >
                <span>Add to Cart</span>
                <span
                  className="ml-2"
                  style={{
                    display: selectedItem.quantity === 0 ? "none" : "",
                  }}
                >
                  +&nbsp;{selectedItem.quantity}
                </span>
              </button>
            </div>
          </div>
          <div className="col-12 col-lg-5 col-xl-6 product-detail-container">
            <h2 className="mb-3">{selectedItem.name}</h2>
            <p className="product-detail-description">{selectedItem.details}</p>
            <div className="d-flex">
              <p className="mt-2">
                Price : &#8361;
                {selectedItem.price}
              </p>
            </div>
            <div>
              <h6 className="mt-2">Ingredients</h6>
              <p>{selectedItem.ingredients}</p>
            </div>
          </div>
        </div>
      </div>
    );
  }
  getSelectedItem() {
    const { productItems, match } = this.props;
    return productItems.filter(
      (item) => item.id === parseInt(match.params.id)
    )[0];
  }
}

export default ProductDetailsComponent;
