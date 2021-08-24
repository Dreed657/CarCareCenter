import { EngineType } from './enums/EngineType';

export default interface CarShortDto {
    id: number;
    type: EngineType;
    createdAt: Date;
    repairmentsSize: number;
    year: number;
    vin: string;
    manufacturer: string;
    model: string;
}