/* eslint-disable react-hooks/rules-of-hooks */
import React from "react";
import { useState } from "react";
import { Link } from "react-router-dom";
import { Button, Modal } from "react-bootstrap";

const BootstrapModal = (props) => {
  const [showHide, setShowHide] = useState(false);

  const handleModalShowHide = () => {
    if (showHide) this.props.onBuyItem();
    setShowHide(!showHide);
  };

  const handleModalClose = () => {
    setShowHide(false);
    //this.setState({ showHide: this.state.ShowHide });
  };

  const showText = () => {
    if (props.totalPrice === undefined) {
      return <span>ORDER</span>;
    } else {
      return <span>ORDER</span>;
    }
  };

  return (
    <div>
      <Button
        variant="light"
        style={{ display: props.totalPrice === 0 ? "none" : "" }}
        disabled
      >
        <span>
          Total Price : &#8361;
          {props.totalPrice}
        </span>
      </Button>
      <br />
      <Link to={"/"}>
        <Button
          variant="secondary"
          onClick={() => handleModalShowHide()}
          style={{ display: props.totalPrice === 0 ? "none" : "" }}
        >
          Additional Order
        </Button>
      </Link>
      &nbsp;&nbsp;
      <Button
        variant="info"
        onClick={() => handleModalShowHide()}
        style={{ display: props.totalPrice === 0 ? "none" : "" }}
      >
        {showText()}
      </Button>
      <Modal show={showHide} centered>
        <Modal.Header closeButton onClick={() => handleModalClose()}>
          <Modal.Title>ORDER</Modal.Title>
        </Modal.Header>

        <Modal.Body>주문하시겠습니까?</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => handleModalClose()}>
            Close
          </Button>
          <Button
            variant="info"
            type="submit"
            onClick={() => {
              props.onBuyItem();
              props.cleanCartItem();
              handleModalClose();
            }}
          >
            ORDER
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default BootstrapModal;
