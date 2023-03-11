import React from "react";
import Actions from "./Actions";
import Profile from "./Profile";

const CustomerProfile = () => {
  return (
    <div className="row">
      <div className="col-lg-6">
        <Profile />
      </div>
      <div className="col-lg-6">
        <Actions />
      </div>
    </div>
  );
};

export default CustomerProfile;
