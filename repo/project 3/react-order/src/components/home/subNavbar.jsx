/* eslint-disable no-restricted-globals */
import React, { Component } from "react";
import { allCategories } from "../../const/productConst";
import "./../../style/subNavbar.css";

class SubNavbarComponent extends Component {
  state = {};
  render() {
    return (
      <div
        style={{
          display:
            this.props.searchField || this.props.productList.length === 0
              ? "none"
              : "",
        }}
      >
        <ul className="nav mt-4 mb-4">
          {Object.values(allCategories).map((navItem) => (
            <li key={navItem} className="nav-item">
              <h6
                onClick={() => this.props.onSelectItem(navItem)}
                className={
                  this.props.selectedItem === navItem
                    ? "nav-link active"
                    : "nav-link"
                }
                style={
                  this.props.selectedItem === navItem
                    ? { cursor: "pointer", color: "#39ada8" }
                    : { cursor: "pointer" }
                }
              >
                {navItem}
              </h6>
            </li>
          ))}
        </ul>
      </div>
    );
  }
}

export default SubNavbarComponent;
