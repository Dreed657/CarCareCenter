import React, {useEffect, useState} from "react";
import {useHistory} from "react-router-dom";

import {
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
} from "@material-ui/core";
// @ts-ignore
import ReactTimeAgo from 'react-time-ago'

import PageLayout from "../../components/page-layout";
import Loader from "../../components/loader";

import CarDto from "../../models/CarDto";
import CarService from "../../services/CarService";

const HomePage = () => {
    const [cars, setCars] = useState<CarDto[]>([]);

    const history = useHistory();

    useEffect(() => {
        CarService.getAll().then((res) => {
            setCars(res.data);
        });
    }, []);

    const handleClick = (id: number) => {
        history.push("/car/" + id);
    };

    return (
        <PageLayout>
            <TableContainer component={Paper}>
                <Table aria-label="simple table">
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
                        <TableBody>
                            {cars.map((car) => (
                                <TableRow key={car.id} hover onClick={() => handleClick(car.id)}>
                                    <TableCell>{car.id}</TableCell>
                                    <TableCell>{car.manufacturer}</TableCell>
                                    <TableCell>{car.model}</TableCell>
                                    <TableCell>{car.vin}</TableCell>
                                    <TableCell>{car.type}</TableCell>
                                    <TableCell>{car.year}</TableCell>
                                    <TableCell><ReactTimeAgo date={car?.createdAt} locale="en"/></TableCell>
                                    <TableCell>{car.repairments.length}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    ) : (
                        <Loader/>
                    )}
                </Table>
            </TableContainer>
        </PageLayout>
    );
};

export default HomePage;
