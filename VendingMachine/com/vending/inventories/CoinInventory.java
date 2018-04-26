package com.vending.inventories;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.vending.constants.Coin;
import com.vending.exception.NoSuchProductException;
import com.vending.exception.OutOfStockException;

public class CoinInventory {
	private Map<String, Integer> bucket;

	public CoinInventory() {
		this.bucket = new HashMap<>();
	}

	public void addCoins(String coinType, int quantity) {
		if (bucket.containsKey(coinType)) {
			int currentQuant = bucket.get(coinType);
			bucket.put(coinType, currentQuant + quantity);
		} else {
			bucket.put(coinType, quantity);
		}
	}

	
	public void clearCoinInventory() {
		bucket.clear();
	}

	public void ClearCoin(String coinType) throws NoSuchProductException {
		if (bucket.containsKey(coinType)) {
			bucket.remove(coinType);
		} else {
			throw new NoSuchProductException(coinType + " does not exist !!");
		}
	}

	public void addAndArrangeMoney(int money) {
		try {
			while (money != 0) {

				if (money >= Coin.QUATER.getDenomination() && hasCoin(Coin.QUATER.toString())) {
					addCoin(Coin.QUATER.toString());
					money = money - Coin.QUATER.getDenomination();
				}

				if (money >= Coin.DIME.getDenomination() && hasCoin(Coin.DIME.toString())) {
					addCoin(Coin.DIME.toString());
					money = money - Coin.DIME.getDenomination();
				}

				if (money >= Coin.NICKEL.getDenomination() && hasCoin(Coin.NICKEL.toString())) {
					addCoin(Coin.NICKEL.toString());
					money = money - Coin.NICKEL.getDenomination();
				}

				if (money >= Coin.PENNY.getDenomination() && hasCoin(Coin.PENNY.toString())) {
					addCoin(Coin.PENNY.toString());
					money = money - Coin.PENNY.getDenomination();
				}
			}

		} catch (NoSuchProductException e) {
			System.out.println(e.getMessage());
		}

	}

	public void addCoin(String coinType) throws NoSuchProductException {
		if (bucket.containsKey(coinType)) {
			int currentVal = bucket.get(coinType);
			bucket.put(coinType, ++currentVal);
		} else {
			throw new NoSuchProductException("Coin does not exist");
		}

	}

	public void deductCoin(String coinType) throws NoSuchProductException, OutOfStockException {
		if (bucket.containsKey(coinType)) {
			int currentVal = bucket.get(coinType);
			if (currentVal > 0) {
				bucket.put(coinType, --currentVal);
			} else {
				throw new OutOfStockException(coinType + " is not available now");
			}

		} else {
			throw new NoSuchProductException("Coin does not exist");
		}
	}

	public int getMoney(List<Coin> coins) {
		if (coins != null) {
			return coins.stream().map(coin -> coin.getDenomination()).reduce(0, Integer::sum);
		} else {
			return -1;
		}

	}

	public List<Coin> getRefund(int money) throws NoSuchProductException, OutOfStockException {
		List<Coin> change = new ArrayList<>();
		while (money != 0) {
			if (money >= Coin.QUATER.getDenomination() && hasCoin(Coin.QUATER.toString())
					&& getCoinDetail(Coin.QUATER.toString())>0) {
				System.out.println(hasCoin(Coin.QUATER.toString()));
				change.add(Coin.QUATER);
				money = money - Coin.QUATER.getDenomination();
				deductCoin(Coin.QUATER.toString());
			}

			if (money >= Coin.DIME.getDenomination() && hasCoin(Coin.DIME.toString())
					&& getCoinDetail(Coin.DIME.toString())>0) {
				change.add(Coin.DIME);
				money = money - Coin.DIME.getDenomination();
				deductCoin(Coin.DIME.toString());
			}

			if (money >= Coin.NICKEL.getDenomination() && hasCoin(Coin.NICKEL.toString())
					&& getCoinDetail(Coin.NICKEL.toString())>0) {
				change.add(Coin.NICKEL);
				money = money - Coin.NICKEL.getDenomination();
				deductCoin(Coin.NICKEL.toString());
			}

			if (money >= Coin.PENNY.getDenomination() && hasCoin(Coin.PENNY.toString())
					&& getCoinDetail(Coin.PENNY.toString())>0) {

				change.add(Coin.PENNY);
				money = money - Coin.PENNY.getDenomination();
				deductCoin(Coin.PENNY.toString());
			}
		}

		return change;

	}
	public int getCoinDetail(String coinType)throws NoSuchProductException{
		if(bucket.containsKey(coinType)){
			return bucket.get(coinType);
		}else {
			throw new NoSuchProductException(coinType+" does not exist");
		}
	}
	
	
	public boolean hasCoin(String coinType) {
		if (bucket.containsKey(coinType)) {
			return true;
		} else {
			return false;
		}
	}


}
