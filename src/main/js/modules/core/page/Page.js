import React from 'react';
import {Route, Switch} from "react-router-dom";
import Foods from "./Foods";
import OthersFoodHistory from "./OthersFoodHistory";
import Users from "./Users";
import YourFoodHistory from "./YourFoodHistory";

const Page = () => {
  return (
    <Switch>
      <Route path="/foods" component={Foods}/>
      <Route path="/others-food-history" component={OthersFoodHistory}/>
      <Route path="/users" component={Users}/>
      <Route path="/your-food-history" component={YourFoodHistory}/>
    </Switch>
  )

};

export default Page;