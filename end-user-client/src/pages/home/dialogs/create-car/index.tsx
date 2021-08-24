import React, { useState } from 'react';
import {
    Button,
    Chip,
    CircularProgress,
    createStyles,
    Dialog,
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
    Fab,
    makeStyles,
    TextField,
    Theme,
} from '@material-ui/core';

import AddIcon from '@material-ui/icons/Add';

import { green } from '@material-ui/core/colors';
import { EngineType } from '../../../../models/enums/EngineType';
import CarService from '../../../../services/CarService';
import CarInputDto from '../../../../models/CarInputDto';

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        fab: {
            position: 'absolute',
            bottom: theme.spacing(2),
            right: theme.spacing(2),
        },
        wrapper: {
            margin: theme.spacing(1),
            position: 'relative',
        },
        buttonSuccess: {
            backgroundColor: green[500],
            '&:hover': {
                backgroundColor: green[700],
            },
        },
        buttonProgress: {
            color: green[500],
            position: 'absolute',
            top: '50%',
            left: '50%',
            marginTop: -12,
            marginLeft: -12,
        },
        formRow: {
            '& .MuiTextField-root': {
                margin: theme.spacing(1),
                width: '25ch',
            },
        },
        errorCloud: {
            display: 'flex',
            justifyContent: 'center',
            flexWrap: 'wrap',
            '& > *': {
                margin: theme.spacing(0.5),
            },
        },
    }),
);

const CreateCarDialog = () => {
    const classes = useStyles();
    const [open, setOpen] = useState<boolean>(false);

    const [loading, setLoading] = useState<boolean>(false);
    const [errors, setErrors] = useState<string[]>([]);

    // Form controls
    const [vin, setVin] = useState<string>('');
    const [manufacturer, setManufacturer] = useState<string>('');
    const [model, setModel] = useState<string>('');
    const [year, setYear] = useState<number>();
    const [engineType, setEngineType] = useState<number>(0);

    const handleVinChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setVin(event.target.value);
    };
    const handleManufacturerChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setManufacturer(event.target.value);
    };
    const handleModelChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setModel(event.target.value);
    };
    const handleYearChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setYear(parseInt(event.target.value));
    };
    const handleEngineTypeChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setEngineType(parseInt(event.target.value));
    };

    const handleSubmit = () => {
        setLoading(true);
        const data: CarInputDto = {
            vin,
            manufacturer,
            model,
            year: !year ? 2014 : year,
            type: engineType,
        };

        CarService.createCar(data)
            .then(res => {
                console.log(res);
                setLoading(false);
                // setOpen(false);
            }).catch(e => {
            console.log(e.response.data.details);
            setLoading(false);
            setErrors(e.response.data.details);
        });
    };

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    // TODO: Refactor
    const engineTypeStrings = Object.keys(EngineType)
        .filter(v => isNaN(Number(v)));
    const engineTypeValues = Object.keys(EngineType)
        .filter(v => !isNaN(Number(v)));

    return (
        <>
            <Fab className={classes.fab} color='primary' aria-label='add' onClick={handleClickOpen}>
                <AddIcon />
            </Fab>
            <Dialog open={open} onClose={handleClose} aria-labelledby='form-dialog-title'>
                <DialogTitle id='form-dialog-title'>Create Car</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        To subscribe to this website, please enter your email address here. We will send updates
                        occasionally.
                        <div className={classes.errorCloud}>
                            {errors.map(e => {
                                return (<Chip
                                    label={e}
                                    color='secondary'
                                />);
                            })}
                        </div>
                    </DialogContentText>
                    <form onSubmit={handleSubmit} className={classes.formRow}>
                        <TextField
                            label='Vin'
                            placeholder='1M1AA13Y6VW098948'
                            value={vin}
                            onChange={handleVinChange}
                            helperText='Please insert valid vin number.'
                            variant='outlined'
                        />
                        <TextField
                            label='Manufacturer'
                            placeholder='Audi | BMW | Mercedes'
                            value={manufacturer}
                            onChange={handleManufacturerChange}
                            helperText='Please insert valid car manufacturer number.'
                            variant='outlined'
                        />
                        <TextField
                            label='Model'
                            placeholder='S4'
                            value={model}
                            onChange={handleModelChange}
                            helperText='Please insert valid car model number.'
                            variant='outlined'
                        />
                        <TextField
                            label='Year'
                            placeholder='2014'
                            value={year}
                            onChange={handleYearChange}
                            helperText='Please insert valid year of manufacturing number.'
                            variant='outlined'
                        />
                        <TextField
                            select
                            label='Engine type'
                            value={engineType}
                            onChange={handleEngineTypeChange}
                            SelectProps={{
                                native: true,
                            }}
                            helperText='Please select correct engine type.'
                            variant='outlined'
                        >
                            {engineTypeStrings
                                .map((option, i) => (
                                    <option key={engineTypeValues[i]} value={engineTypeValues[i]}>
                                        {option}
                                    </option>
                                ))}
                        </TextField>
                    </form>
                </DialogContent>
                <DialogActions>
                    <div className={classes.wrapper}>
                        <Button
                            variant='contained'
                            color='primary'
                            className={classes.buttonSuccess}
                            disabled={loading}
                            onClick={handleSubmit}
                        >
                            Submit
                        </Button>
                        {loading && <CircularProgress size={24} className={classes.buttonProgress} />}
                    </div>
                </DialogActions>
            </Dialog>
        </>
    );
};

export default CreateCarDialog;