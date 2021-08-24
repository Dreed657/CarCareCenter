import React, {useEffect, useState} from 'react';
import {
    Chip,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableFooter,
    TableHead, TablePagination,
    TableRow, Typography
} from "@material-ui/core";
import AutorenewIcon from "@material-ui/icons/Autorenew";
import DoneIcon from "@material-ui/icons/Done";

// @ts-ignore
import ReactTimeAgo from 'react-time-ago'

import RepairDto from "../../../../models/RepairDto";
import RepairService from "../../../../services/RepairService";

interface CarRepairsTableProps {
    carId: number;
}

const RepairsTable = (props: CarRepairsTableProps) => {
    const [repairs, setRepairs] = useState<RepairDto[]>([]);
    const [page, setPage] = React.useState<number>(0);
    const [size, setSize] = React.useState<number>(5);

    useEffect(() => {
        RepairService.getByCarId(props.carId).then(res => {
            setRepairs(res.data);
        })
    }, [props])

    const handleChangePage = (event: React.MouseEvent<HTMLButtonElement> | null, newPage: number) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (
        event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>,
    ) => {
        setSize(parseInt(event.target.value, 10));
        setPage(0);
    };

    return (
        <TableContainer component={Paper}>
            <Table aria-label="custom pagination table">
                <TableHead>
                    <TableRow>
                        <TableCell>Id</TableCell>
                        <TableCell>Mileage</TableCell>
                        <TableCell>Added ago</TableCell>
                        <TableCell>Status</TableCell>
                        <TableCell>Total</TableCell>
                        <TableCell>Items</TableCell>
                    </TableRow>
                </TableHead>
                {repairs.length > 0 ? <>
                    <TableBody>
                        {repairs.slice(page * size, page * size + size).map((repair) =>
                            (<TableRow key={repair.id} hover>
                                <TableCell>{repair.id}</TableCell>
                                <TableCell>{repair.mileage}</TableCell>
                                <TableCell><ReactTimeAgo date={repair?.createdAt}
                                                         locale="en"/></TableCell>
                                <TableCell>
                                    {repair.status.toString() === "PROGRESS" ? (
                                        <Chip label="In-Progress" variant="outlined" color="primary"
                                              size="small" icon={<AutorenewIcon/>}/>
                                    ) : (
                                        <Chip label="Finished" variant="outlined" color="secondary"
                                              size="small" icon={<DoneIcon/>}/>
                                    )}
                                </TableCell>
                                <TableCell>{repair.totalPrice}</TableCell>
                                <TableCell>{repair.items.length}</TableCell>
                            </TableRow>)
                        )}
                    </TableBody>
                    <TableFooter>
                        <TableRow>
                            <TablePagination
                                rowsPerPageOptions={[5, 10, 25, {label: 'All', value: repairs.length}]}
                                colSpan={3}
                                count={repairs.length}
                                rowsPerPage={size}
                                page={page}
                                SelectProps={{
                                    inputProps: {'aria-label': 'rows per page'},
                                    native: true,
                                }}
                                onPageChange={handleChangePage}
                                onRowsPerPageChange={handleChangeRowsPerPage}
                            />
                        </TableRow>
                    </TableFooter>
                </> : (
                    <TableRow>
                        <Typography variant="h5" gutterBottom align={"center"}>
                            No repairs added to this car!
                        </Typography>
                    </TableRow>
                )}
            </Table>
        </TableContainer>
    )
}

export default RepairsTable;