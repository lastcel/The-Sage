package sagemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import sagemod.powers.SageFlight;

public class ByrdCarpet extends AbstractSageRelic {

	public static final String ID = "Byrd_Carpet";
	public static final RelicTier TIER = RelicTier.BOSS;
	public static final LandingSound SOUND = LandingSound.MAGICAL;
	private static final int FLY_AMT = 3;
	private static final int SET_TO_FLY_AMT_WHEN1 = 1;
	private static final int SET_TO_FLY_AMT_WHEN2 = 2;

	public ByrdCarpet() {
		super(ID, TIER, SOUND);
	}

	@Override
	public void obtain() {
		if (player() != null && player().hasRelic(FlyingCarpet.ID)) {
			int index = player().relics.indexOf(player().getRelic(FlyingCarpet.ID));
			instantObtain(player(), index, true);
		} else {
			super.obtain();
		}
	}

	@Override
	public void atTurnStart() {
		if (player().hasPower(SageFlight.POWER_ID)) {
			SageFlight power = (SageFlight) player().getPower(SageFlight.POWER_ID);
			if (power.amount == SET_TO_FLY_AMT_WHEN1 || power.amount == SET_TO_FLY_AMT_WHEN2) {
				flash();
				appearAbove(player());
				int amount = FLY_AMT - power.amount;
				AbstractDungeon.actionManager.addToBottom(
						new ApplyPowerAction(player(), player(), new SageFlight(player(), amount), amount));
			}
		}
	}

	@Override
	public void atBattleStartPreDraw() {
		flash();
		appearAbove(player());
		applyPowerToSelf(new SageFlight(player(), FLY_AMT));
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0] + FLY_AMT + DESCRIPTIONS[1] + SET_TO_FLY_AMT_WHEN1 + DESCRIPTIONS[2]
				+ SET_TO_FLY_AMT_WHEN2 + DESCRIPTIONS[3] + FLY_AMT + DESCRIPTIONS[4];
	}

	@Override
	public AbstractRelic makeCopy() {
		return new ByrdCarpet();
	}

}