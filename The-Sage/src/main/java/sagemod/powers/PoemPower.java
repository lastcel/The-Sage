package sagemod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import sagemod.actions.ExecuteLaterAction;

public class PoemPower extends AbstractSagePower {

	public static final String POWER_ID = "sagemod:Poem";
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	
	public PoemPower(AbstractCreature owner, int amount) {
		super(POWER_ID, NAME, owner, amount);
		updateDescription();
		type = AbstractPower.PowerType.DEBUFF;
	}
	
	@Override
	public void onAfterCardPlayed(AbstractCard usedCard) {
		addToBot(new ExecuteLaterAction(() ->  {
			flash();
			AbstractDungeon.player.energy.use(amount);
			addToBot(new RemoveSpecificPowerAction(owner, owner, this));
		}));
	}
	
	@Override
	public void atEndOfTurn(boolean isPlayer) {
		if (isPlayer) {
			addToBot(new RemoveSpecificPowerAction(owner, owner, this));
		}
	}
	
	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
	}

}
