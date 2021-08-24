import { EngineType } from './enums/EngineType';

export default interface CarInputDto {
    vin: string;
    year: number;
    manufacturer: string;
    model: string;
    type: EngineType;
}