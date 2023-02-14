/* eslint-disable react-hooks/rules-of-hooks */
/* eslint-disable no-unreachable */
/* eslint-disable no-restricted-globals */
import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faShoppingCart } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import { Button } from "react-bootstrap";
import "./../style/navbar.css";

const NavbarComponent = (props) => {
  return (
    <React.Fragment>
      <nav className="navbar navbar-dark bg-dark">
        <div className="container">
          <Link className="navbar-brand mr-auto" to="/" title="Home">
            RESTAURANT
          </Link>
          <Link to="/cart">
            <button type="button" className="btn btn-secondary" title="Cart">
              <span className="mr-1">
                CART&nbsp;&nbsp;&nbsp;
                <FontAwesomeIcon icon={faShoppingCart} />
              </span>
              <span
                className="badge badge-info"
                style={{
                  display:
                    getTotalCartItems(props.productItems) === 0 ? "none" : "",
                }}
              >
                {getTotalCartItems(props.productItems)}
              </span>
            </button>
          </Link>
          &nbsp;&nbsp;
          {/* <Button onClick={() => (location.href = "http://localhost:5000/")}>
            OCR CHECK
          </Button> */}
        </div>
      </nav>
    </React.Fragment>
  );

  function getTotalCartItems(productItems) {
    return productItems.filter((item) => item.quantity > 0).length;
  }
};

export default NavbarComponent;
