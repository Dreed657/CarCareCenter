import React from "react";
import { Container } from "@material-ui/core";

import Header from "../header";

const PageLayout = (props: any) => {
  return (
    <Container maxWidth="lg">
      <Header></Header>
      <main>{props.children}</main>
    </Container>
  );
};

export default PageLayout;
