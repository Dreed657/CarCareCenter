import { EngineType } from './enums/EngineType';
import RepairDto from './RepairDto';

export default interface CarDto {
    id: number;
    type: EngineType;
    createdAt: Date;
    repairments: RepairDto[];
    year: number;
    vin: string;
    manufacturer: string;
    model: string;
}
