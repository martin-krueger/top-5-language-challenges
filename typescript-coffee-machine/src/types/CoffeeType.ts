/**
 * A CoffeeType has a name and uses resources (beans, water).
 */
export class CoffeeType {

    readonly name: string
    readonly beans: number
    readonly water: number

    constructor(name: string, beans: number, water: number) {
        this.name = name
        this.beans = beans
        this.water = water
    }

}