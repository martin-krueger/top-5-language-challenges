import {Resource} from "./Resource";
import {CoffeeType} from "./CoffeeType";

export enum Failure {
    NONE,
    NO_WATER,
    NO_BEANS,
    UNKNOWN_COFFEE_TYPE
}

export const Coffee = new CoffeeType("Coffee", 50, 0.2)
export const Americano = new CoffeeType("Americano", 40, 0.2)
export const Espresso = new CoffeeType("Espresso", 100, 0.05)

/**
 * A coffee machine which makes coffee of different types (Coffee, Americano and Espresso).
 */
export class CoffeeMachine {

    private coffeeTypes = [Coffee, Americano, Espresso]
    private beans = new Resource(250, 250)
    private minBeansAmount: number
    private water = new Resource(4, 4)
    private minWaterAmount: number

    constructor() {
        this.minBeansAmount = this.coffeeTypes[0].beans
        this.coffeeTypes.forEach(type => type.beans < this.minBeansAmount ? this.minBeansAmount = type.beans : this.minBeansAmount)

        this.minWaterAmount = this.coffeeTypes[0].water
        this.coffeeTypes.forEach(type => type.water < this.minWaterAmount ? this.minWaterAmount = type.water : this.minWaterAmount)
    }

    /**
     * Checks if this coffee machine can make the given coffee type. If it is possible this methods returns result=true and
     * failure=Failure.NONE, if not result=false and a specific failure reason.
     * @param type the coffee type to check
     */
    canMakeCoffee(type: CoffeeType): { result: boolean, failure: Failure } {
        if (!this.coffeeTypes.includes(type)) {
            return {result: false, failure: Failure.UNKNOWN_COFFEE_TYPE}
        }

        if (this.beans.getCurrentAmount() < type.beans) {
            return {result: false, failure: Failure.NO_BEANS}
        }

        if (this.water.getCurrentAmount() < type.water) {
            return {result: false, failure: Failure.NO_WATER}
        }

        return {result: true, failure: Failure.NONE}
    }

    /**
     *
     * @param type
     */
    makeCoffee(type: CoffeeType): Promise<Failure> {
        const coffeeMachine = this;
        return new Promise((resolve, reject) => {
            const canMake = coffeeMachine.canMakeCoffee(type)

            if (!canMake.result) {
                reject.apply(canMake.failure)
                return {madeCoffee: false, failure: canMake.failure}
            }

            coffeeMachine.beans.take(type.beans)
            coffeeMachine.water.take(type.water)

            setTimeout(() => resolve.apply(Failure.NONE), 8000)
        })
    }

    /**
     * Returns the bean resource.
     */
    getBeans(): Resource {
        return this.beans
    }

    /**
     * Returns the water resource.
     */
    getWater(): Resource {
        return this.water
    }

    /**
     * Returns the minimum amount of water this coffee machine should have.
     */
    getMinimumWaterAmount(): number {
        return this.minWaterAmount
    }

    /**
     * Returns the minimum amount of beans this coffee machine should have.
     */
    getMinimumBeansAmount(): number {
        return this.minBeansAmount
    }

}
