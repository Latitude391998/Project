import React, { Component } from "react";
import axios from "axios";

class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      customer: {}
    };
  }

  componentDidMount() {
    axios.get("/customer").then((res) => {
      const customer = res.data;
      this.setState({ customer });
    });
  }

  render() {
    return (
      <div className="card">
        <div className="card-header">
          <h5 className="card-title">Profile</h5>
        </div>
        <div className="card-body">
          <p>Name: {this.state.customer.name}</p>
          <p>Email: {this.state.customer.email}</p>
          <p>Contact Number: {this.state.customer.contactNumber}</p>
          <p>Address: {this.state.customer.address}</p>
          <button className="btn btn-primary">Edit Profile</button>
          <button className="btn btn-danger">Delete Profile</button>
        </div>
      </div>
    );
  }
}

export default Profile;
