import React from "react";
import { AppBar, Button, Toolbar, Typography } from "@material-ui/core";

const PageLayout = (props: any) => {
  return (
    <AppBar position="static" color="default">
      <Toolbar>
        <Typography variant="h6" color="inherit">
          CarCareService
        </Typography>
        <Button variant="contained" color="primary">
          Home
        </Button>
        <Button variant="contained" color="primary">
          Stats
        </Button>
      </Toolbar>
    </AppBar>
  );
};

export default PageLayout;
