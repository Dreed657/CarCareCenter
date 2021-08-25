import React, { useState } from 'react';

import {
    Button,
    ButtonGroup,
    createStyles,
    makeStyles,
    TableCell,
    TableRow,
    TextField,
    Theme,
} from '@material-ui/core';
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';
import CheckIcon from '@material-ui/icons/Check';
import CloseIcon from '@material-ui/icons/Close';
import { green, yellow } from '@material-ui/core/colors';

import ItemDto from '../../../../models/ItemDto';
import { Metric } from '../../../../models/enums/Metric';
import ItemService from '../../../../services/ItemService';

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        submitButton: {
            color: 'white',
            backgroundColor: green[500],
        },
        warningButton: {
            color: 'white',
            backgroundColor: yellow[500],
        },
    }),
);

interface ItemRowProps {
    item: ItemDto;

    handleItemDelete(itemId: number): any;
}

const ItemRow = (props: ItemRowProps) => {
    const { item } = props;
    const classes = useStyles();

    const [editMode, setEditMode] = useState<boolean>(false);

    const [desc, setDesc] = useState<string>(item.description);
    const [quantity, setQuantity] = useState<number>(item.quantity);
    const [metric, setMetric] = useState<Metric>(parseInt(Metric[item.metric]));
    const [price, setPrice] = useState<number>(item.price);

    const handleDescChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setDesc(event.target.value);
    };
    const handleQuantityChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setQuantity(parseInt(event.target.value));
    };
    const handleMetricChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setMetric(parseInt(event.target.value));
    };
    const handlePriceChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setPrice(parseInt(event.target.value));
    };

    const handleModeChange = () => {
        setEditMode(!editMode);
    };

    const handleSubmit = () => {
        const data: ItemDto = {
            id: item.id,
            description: desc,
            quantity,
            metric: metric,
            price,
        };

        ItemService.updateItem(data)
            .then(res => {
                console.log(res);
                setEditMode(false);
            })
            .catch(e => {
                console.error(e);
                setEditMode(false);
            });
    };

    // TODO: Refactor
    const metricStrings = Object.keys(Metric)
        .filter(v => isNaN(Number(v)));
    const metricValues = Object.keys(Metric)
        .filter(v => !isNaN(Number(v)));

    return (
        <TableRow key={item.id}>
            {!editMode ? (
                <>
                    <TableCell>{item.id}</TableCell>
                    <TableCell>{desc}</TableCell>
                    <TableCell>{quantity}</TableCell>
                    <TableCell>{Metric[metric]}</TableCell>
                    <TableCell>{price}</TableCell>
                </>
            ) : (
                <>
                    <TableCell>{item.id}</TableCell>
                    <TableCell>
                        <TextField
                            label='Description'
                            size='small'
                            variant='outlined'
                            value={desc}
                            onChange={handleDescChange}
                        />
                    </TableCell>
                    <TableCell>
                        <TextField
                            label='Quantity'
                            size='small'
                            type='number'
                            variant='outlined'
                            value={quantity}
                            onChange={handleQuantityChange}
                        />
                    </TableCell>
                    <TableCell>
                        <TextField
                            select
                            label='Metric'
                            size='small'
                            variant='outlined'
                            value={metric}
                            onChange={handleMetricChange}
                            SelectProps={{
                                native: true,
                            }}
                        >
                            {metricStrings
                                .map((option, i) => (
                                    <option key={metricValues[i]} value={metricValues[i]}>
                                        {option}
                                    </option>
                                ))}
                        </TextField>
                    </TableCell>
                    <TableCell>
                        <TextField
                            label='Price'
                            size='small'
                            variant='outlined'
                            value={price}
                            onChange={handlePriceChange}
                        />
                    </TableCell>
                </>
            )}

            <TableCell>
                <ButtonGroup variant='contained' aria-label='contained primary button group'>
                    {!editMode ? (
                        <Button onClick={() => handleModeChange()} className={classes.warningButton}>
                            <EditIcon />
                        </Button>
                    ) : (
                        [
                            <Button key={1} onClick={() => handleModeChange()} color={'primary'}>
                                <CloseIcon />
                            </Button>,
                            <Button key={2} onClick={() => handleSubmit()} className={classes.submitButton}>
                                <CheckIcon />
                            </Button>,
                        ]
                    )}
                    <Button onClick={() => props.handleItemDelete(item.id)} color={'secondary'}>
                        <DeleteIcon />
                    </Button>
                </ButtonGroup>
            </TableCell>
        </TableRow>
    );
};

export default ItemRow;