import React from 'react';

import PageLayout from '../../components/page-layout';
import CarTable from './tables/car-table';
import CreateCarDialog from './dialogs/create-car';

const HomePage = () => {
    return (
        <>
            <PageLayout>
                <CarTable />
            </PageLayout>
            <CreateCarDialog/>
        </>
    );
};

export default HomePage;
