/* eslint-disable jsx-a11y/alt-text */
import React, { Component, useEffect, useState } from "react";
import ProductCartItemComponent from "./productItem";
import SubNavbarComponent from "./subNavbar";
import { paginate } from "../../utils/paginate";
import { allCategories } from "../../const/productConst";
import "./../../style/noResult.css";
import MoonLoader from "react-spinners/MoonLoader";

class ProductCartItemsComponent extends Component {
  state = {
    pageSize: 5000,
    currentIndex: 1,
    selectedItem: allCategories.ALL_PRODUCTS,
  };

  render() {
    const { selectedItem } = this.state;
    let productList = this.getProductList();
    return (
      <React.Fragment>
        <SubNavbarComponent
          searchField={this.props.searchField}
          productList={productList}
          selectedItem={selectedItem}
          onSelectItem={this.handleSelectItem}
        />
        <div className="d-flex flex-wrap mt-5 b-4 justify-content-center">
          {this.renderFilteredItems(productList)}
        </div>
        <this.TopButton />
        {/* <div className="d-flex justify-content-center">
          <PaginationComponent
            Items={productList}
            pageSize={pageSize}
            currentIndex={currentIndex}
            onPageChange={this.handlePageChange}
          />
        </div> */}
      </React.Fragment>
    );
  }

  // top 버튼
  TopButton = () => {
    const [showButton, setShowButton] = useState(false);
    const scrollToTop = () => {
      window.scroll({
        top: 0,
        behavior: "smooth",
      });
    };
    useEffect(() => {
      const showButtonClick = () => {
        if (window.scrollY > 10) {
          setShowButton(true);
        } else {
          setShowButton(false);
        }
      };
      window.addEventListener("scroll", showButtonClick);
      return () => {
        window.removeEventListener("scroll", showButtonClick);
      };
    }, []);
    return (
      <>
        {showButton && (
          <div>
            <h1>
              <img
                src={process.env.PUBLIC_URL + "/top.jpg"}
                onClick={scrollToTop}
                style={{
                  position: "fixed",
                  bottom: "40px",
                  width: "50px",
                  right: "10%",
                }}
              />
            </h1>
          </div>
        )}
      </>
    );
  };

  handlePageChange = (page) => {
    this.setState({ currentIndex: page });
  };

  handleSelectItem = (navItem) => {
    this.setState({ selectedItem: navItem });
    this.setState({ currentIndex: 1 });
  };

  getProductList() {
    const { selectedItem } = this.state;
    const { productItems, searchField } = this.props;
    if (searchField === "") {
      // All -> except 'Wine' & 'Whiskey'
      // default start
      return selectedItem === allCategories.ALL_PRODUCTS
        ? productItems.filter(
            (item) =>
              item.categories === "Appetizer" ||
              item.categories === "Steak" ||
              item.categories === "Pasta" ||
              item.categories === "Pizza" ||
              item.categories === "Side" ||
              item.categories === "Soft Drink" ||
              item.categories === "Wine" ||
              item.categories === "Whiskey"
          )
        : productItems.filter((item) => item.categories === selectedItem);
      // default end
    } else {
      return productItems.filter((item) =>
        item.details.toString().toLowerCase().startsWith(searchField)
      );
    }
  }

  getNoResultIcon() {
    const { searchField } = this.props;
    let icon = "no-result-icon mb-3 fa ";
    return searchField === ""
      ? icon + "fa-shopping-basket"
      : icon + "fa-search";
  }

  renderFilteredItems(productList) {
    const { currentIndex, pageSize } = this.state;
    const { searchField } = this.props;
    if (productList.length === 0) {
      return (
        <>
          <div className="d-flex justify-content-center align-items-center no-result-container">
            <div className="d-flex flex-column">
              <h5 className="no-result-text">
                {searchField === "" ? (
                  <MoonLoader
                    color="#0069d9"
                    cssOverride={{}}
                    loading
                    size={100}
                    speedMultiplier={0.7}
                  />
                ) : (
                  "No search result found"
                )}
              </h5>
            </div>
          </div>
          <div className="d-flex justify-content-center align-items-center"></div>
        </>
      );
    } else {
      const filteredItems = paginate(productList, currentIndex, pageSize);
      return filteredItems.map((item) => (
        <ProductCartItemComponent
          key={item.id}
          Items={item}
          onAddItem={this.props.onAddItem}
        />
      ));
    }
  }
}

export default ProductCartItemsComponent;
