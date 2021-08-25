import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import {
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableFooter,
    TableHead,
    TablePagination,
    TableRow,
} from '@material-ui/core';

// @ts-ignore
import ReactTimeAgo from 'react-time-ago';

import Loader from '../../../../components/loader';

import CarService from '../../../../services/CarService';
import CarDto from '../../../../models/CarDto';

const CarTable = () => {
    const history = useHistory();

    const [cars, setCars] = useState<CarDto[]>([]);
    const [total, setTotal] = useState<number>(0);
    const [page, setPage] = React.useState<number>(0);
    const [size, setSize] = React.useState<number>(5);

    useEffect(() => {
        CarService.getCount().then(res => {
            setTotal(res.data);
        });

        CarService.getAll(page, size).then((res) => {
            setCars(res.data);
        });
    }, [page, size]);

    const handleChangePage = (event: React.MouseEvent<HTMLButtonElement> | null, newPage: number) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (
        event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>,
    ) => {
        setSize(parseInt(event.target.value, 10));
        setPage(0);
    };

    const handleClick = (id: number) => {
        history.push('/car/' + id);
    };

    return (
        <TableContainer component={Paper}>
            <Table stickyHeader aria-label='simple table'>
                <TableHead>
                    <TableRow>
                        <TableCell>Id</TableCell>
                        <TableCell>Manufacturer</TableCell>
                        <TableCell>Model</TableCell>
                        <TableCell>VIN</TableCell>
                        <TableCell>Type</TableCell>
                        <TableCell>Year</TableCell>
                        <TableCell>Added ago</TableCell>
                        <TableCell>Repairments</TableCell>
                    </TableRow>
                </TableHead>
                {cars.length !== 0 ? (
                    <>
                        <TableBody>
                            {cars.map((car) => (
                                <TableRow key={car.id} hover onClick={() => handleClick(car.id)}>
                                    <TableCell>{car.id}</TableCell>
                                    <TableCell>{car.manufacturer}</TableCell>
                                    <TableCell>{car.model}</TableCell>
                                    <TableCell>{car.vin}</TableCell>
                                    <TableCell>{car.type}</TableCell>
                                    <TableCell>{car.year}</TableCell>
                                    <TableCell><ReactTimeAgo date={car?.createdAt} locale='en' /></TableCell>
                                    <TableCell>{car.repairments.length}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                        <TableFooter>
                            <TableRow>
                                <TablePagination
                                    rowsPerPageOptions={[5, 10, 25, { label: 'All', value: total }]}
                                    colSpan={4}
                                    count={total}
                                    rowsPerPage={size}
                                    page={page}
                                    SelectProps={{
                                        inputProps: { 'aria-label': 'rows per page' },
                                        native: true,
                                    }}
                                    onPageChange={handleChangePage}
                                    onRowsPerPageChange={handleChangeRowsPerPage}
                                />
                            </TableRow>
                        </TableFooter>
                    </>
                ) : (
                    <Loader />
                )}
            </Table>
        </TableContainer>
    );
};

export default CarTable;