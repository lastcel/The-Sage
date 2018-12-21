package sagemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class AntiAncientAttack extends AbstractSageCard {

	public static final String ID = "Anti_Ancient_Attack";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 3;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	private static final int ATTACK_DMG = 18;
	private static final int UPGRADE_ATTACK_DMG = 7;

	public AntiAncientAttack() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		baseDamage = ATTACK_DMG;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_ATTACK_DMG);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new AntiAncientAttack();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		int times = 0;
		if (m.hasPower(ArtifactPower.POWER_ID)) {
			times += m.getPower(ArtifactPower.POWER_ID).amount;
		}
		for (int i = 0; i < times; i++) {
			attack(m, AttackEffect.SMASH);
		}
	}

	@Override
	public String getLoadedDescription() {
		return DESCRIPTION;
	}
}