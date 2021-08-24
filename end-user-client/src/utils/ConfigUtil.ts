export interface IEnvConfig {
    API_URL?: string;
}

export const config: IEnvConfig = require('../env.json');
