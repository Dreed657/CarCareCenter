import React, { useState } from 'react';

import {
    Box,
    Chip,
    Collapse,
    IconButton,
    makeStyles,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    Typography,
} from '@material-ui/core';

import KeyboardArrowDownIcon from '@material-ui/icons/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@material-ui/icons/KeyboardArrowUp';

// @ts-ignore
import ReactTimeAgo from 'react-time-ago';

import RepairDto from '../../../../models/RepairDto';

import AutorenewIcon from '@material-ui/icons/Autorenew';
import DoneIcon from '@material-ui/icons/Done';
import ItemRow from '../item-row';
import ItemDto from '../../../../models/ItemDto';
import ItemService from '../../../../services/ItemService';

const useRowStyles = makeStyles({
    root: {
        '& > *': {
            borderBottom: 'unset',
        },
    },
});

interface RepairRowProps {
    repair: RepairDto;
}

const RepairRow = (props: RepairRowProps) => {
    const { repair } = props;
    const classes = useRowStyles();

    const [open, setOpen] = useState<boolean>(false);
    const [items, setItems] = useState<ItemDto[]>(repair.items);

    const handleItemDelete = (itemId: number) => {
        ItemService.deleteItem(itemId)
            .then(res => {
                setItems(items.filter((item) => {
                    return item.id !== itemId;
                }));
                console.log("Items: ", items);
                console.log("Res: ", res);
            })
            .catch(e => {
                console.error(e);
            })
    };

    return (
        <>
            <TableRow className={classes.root}>
                <TableCell>
                    <IconButton aria-label='expand row' size='small' onClick={() => setOpen(!open)}>
                        {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                    </IconButton>
                </TableCell>
                <TableCell>{repair.id}</TableCell>
                <TableCell>{repair.mileage}</TableCell>
                <TableCell>
                    <ReactTimeAgo date={repair?.createdAt} locale='en' />
                </TableCell>
                <TableCell>
                    {repair.status.toString() === 'PROGRESS' ? (
                        <Chip label='In-Progress' variant='outlined' color='primary'
                              size='small' icon={<AutorenewIcon />} />
                    ) : (
                        <Chip label='Finished' variant='outlined' color='secondary'
                              size='small' icon={<DoneIcon />} />
                    )}
                </TableCell>
                <TableCell>{repair.totalPrice}</TableCell>
                <TableCell>{items.length}</TableCell>
            </TableRow>
            <TableRow>
                <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
                    <Collapse in={open} timeout='auto' unmountOnExit>
                        <Box margin={1}>
                            <Typography variant='h6' gutterBottom component='div'>
                                Items
                            </Typography>
                            <Table size='small' aria-label='purchases'>
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Id</TableCell>
                                        <TableCell>description</TableCell>
                                        <TableCell>quantity</TableCell>
                                        <TableCell>Metric</TableCell>
                                        <TableCell>Price</TableCell>
                                        <TableCell>Actions</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {items.map((item, i) => (
                                        <ItemRow key={i} item={item} handleItemDelete={handleItemDelete} />
                                    ))}
                                    <TableRow>
                                        <TableCell colSpan={1}>Total</TableCell>
                                        <TableCell align='right'>{repair.totalPrice}</TableCell>
                                    </TableRow>
                                </TableBody>
                            </Table>
                        </Box>
                    </Collapse>
                </TableCell>
            </TableRow>
        </>
    );
};

export default RepairRow;