import React from "react";

import PageLayout from "../../components/page-layout";
import CarTable from "./tables/car-table";

const HomePage = () => {
    return (
        <PageLayout>
            <CarTable/>
        </PageLayout>
    );
};

export default HomePage;
