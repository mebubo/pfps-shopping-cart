package shop.domain

import eu.timepit.refined._
import eu.timepit.refined.api._
import eu.timepit.refined.auto._
import eu.timepit.refined.collection.Size
import eu.timepit.refined.string.MatchesRegex
import eu.timepit.refined.types.string.NonEmptyString
import io.estatico.newtype.macros.newtype

object checkout {
  type CardNamePred       = String Refined MatchesRegex[W.`"^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$"`.T]
  type CardNumberPred     = Long Refined Size[16]
  type CardExpirationPred = Int Refined Size[4]
  type CardCCVPred        = Int Refined Size[3]

  @newtype case class CardName(value: CardNamePred)
  @newtype case class CardNumber(value: CardNumberPred)
  @newtype case class CardExpiration(value: CardExpirationPred)
  @newtype case class CardCCV(value: CardCCVPred)

  case class Card(
      name: CardName,
      number: CardNumber,
      expiration: CardExpiration,
      ccv: CardCCV
  )
}